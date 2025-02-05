package kr.or.ddit.yguniv.bucket;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bucket")
public class BucketController {
	private final BucketConfig BucketConfig;

    @GetMapping("{second}/{token}")
    public ResponseEntity<String> reconfigureBucket(@PathVariable int second,@PathVariable int token) {
    	second = second*60;
    	BucketConfig.reloadBucket(second, token);
        return ResponseEntity.ok("Bucket reload ok");
    }
    @GetMapping("second/{second}/{token}")
    public ResponseEntity<String> reconfigureSecondBucket(@PathVariable int second,@PathVariable int token) {
    	
    	BucketConfig.reloadSecondBucket(second, token);
    	return ResponseEntity.ok("Bucket reload ok");
    }
    
    
}
