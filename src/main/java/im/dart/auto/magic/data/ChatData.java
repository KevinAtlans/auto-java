package im.dart.auto.magic.data;

import im.dart.boot.common.data.Base;
import lombok.Data;

/**
 * <p>描述: 暂无描述信息</p>
 *
 * <p>创建时间：2023-04-14 10:36</p>
 * <p>更新时间：暂无</p>
 *
 * @author Kevin.Xu
 * @version 1.0
 */
@Data
public class ChatData extends Base {
    private String key;
    private String org;
    private String model = "gpt-3.5-turbo";
    private String content;
    private Boolean justContent = false;
}
