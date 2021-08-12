package com.kariyer.forbiddenwords.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Word")
@AllArgsConstructor
@NoArgsConstructor
public class Word implements Serializable {
    @Id
    private String id;
    private String word;
}
