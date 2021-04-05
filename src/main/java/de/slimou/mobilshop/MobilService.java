package de.slimou.mobilshop;

import org.springframework.stereotype.Service;

@Service
public class MobilService {
    private MobilRepository mobilRepository;

    public MobilService(MobilRepository mobilRepository) {
        this.mobilRepository = mobilRepository;
    }
}
