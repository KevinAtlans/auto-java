package im.dart.auto.magic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>描述: 暂无描述信息</p>
 *
 * <p>创建时间：2023-04-14 10:56</p>
 * <p>更新时间：暂无</p>
 *
 * @author Kevin.Xu
 * @version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "config")
public class CusConfig {
    private String model = "gpt-3.5-turbo";
    private String key;
    private String org;
    private String token;
}
