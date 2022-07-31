package com.example.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class logService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(logService.class);
    public void logInfo(String message){
          LOGGER.info(message);
    }
}
