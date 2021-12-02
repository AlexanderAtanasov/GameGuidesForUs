package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Comment;
import com.example.gameGuidesForUs.model.entity.Screenshot;
import com.example.gameGuidesForUs.repository.CommentRepository;
import com.example.gameGuidesForUs.repository.ScreenshotRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.service.ScreenshotService;
import com.example.gameGuidesForUs.service.cloudinary.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ScreenshotServiceImpl implements ScreenshotService {

    private final ScreenshotRepository screenshotRepository;
    private final CloudinaryService cloudinaryService;
    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    public ScreenshotServiceImpl(ScreenshotRepository screenshotRepository, CloudinaryService cloudinaryService, UserRepository userRepository, CommentRepository commentRepository) {
        this.screenshotRepository = screenshotRepository;
        this.cloudinaryService = cloudinaryService;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Screenshot addScreenshot(MultipartFile screenshotFile, String username) throws IOException {

        String screenshotUrl = cloudinaryService.upload(screenshotFile).getUrl();

        Screenshot screenshot = new Screenshot();

        screenshot.setPublicId(screenshotUrl
                        .substring(screenshotUrl.lastIndexOf("/") + 1,
                                screenshotUrl.lastIndexOf(".")))
                .setUrl(screenshotUrl)
                .setUploadedOn(Instant.now())
                .setUploadedBy(userRepository.getByUsername(username));

        return screenshotRepository.save(screenshot);
    }

    @Override
    public void deleteScreenshot(Long screenshotId) {
        cloudinaryService.delete(screenshotRepository.findById(screenshotId).orElse(null).getPublicId());
        screenshotRepository.deleteById(screenshotId);
    }

    @Override
    public List<Long> findAllScreenshotId() {
        List<Long> screenshotIds = screenshotRepository.allScreenshotIds();
        return screenshotIds;
    }

    @Override
    @Transactional
    public void findAllScreenshotsWithoutAssignedCommentsAndDeleteThem() {
        List<Long> screenshotIds = findAllScreenshotId();
        for (Long id : screenshotIds) {

            if (commentRepository.findByScreenshotId(id).orElse(null) == null) {
                cloudinaryService.delete(screenshotRepository.getById(id).getPublicId());
                screenshotRepository.deleteById(id);
            }
        }



    }
}
