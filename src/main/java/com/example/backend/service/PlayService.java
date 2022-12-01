package com.example.backend.service;

import com.example.backend.model.request.PlayRequest;

public interface PlayService {
    void nextQuestion(PlayRequest playRequest);
}
