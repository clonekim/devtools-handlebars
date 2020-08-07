package devtools;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Hero {
    String name;
    int strength;
}
