package kr.momjobgo.eyou.web.dto;

import kr.momjobgo.eyou.utils.StaticMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Token {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class TokenRequest {
        @NotBlank(message = "id"+ StaticMessage.Validator.NOT_BLANK)
        @NotNull(message = "id"+ StaticMessage.Validator.NOT_NULL)
        private String snsId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class TokenResponse {
        private String token;
        private Long id;

    }
}