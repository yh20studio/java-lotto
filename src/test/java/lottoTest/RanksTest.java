package lottoTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.List;
import lotto.Rank;
import lotto.Ranks;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class RanksTest {

    @Test
    void 당첨_통계_기능_테스트() {
        List<Rank> result = List.of(Rank.MATCH_SIX_NUMBERS, Rank.MATCH_FIVE_AND_BONUS_NUMBERS, Rank.MATCH_FIVE_NUMBERS);
        Ranks ranks = new Ranks(result);
        EnumMap<Rank, Integer> statistics = ranks.getStatistics();

        assertThat(statistics.get(Rank.MATCH_SIX_NUMBERS)).isEqualTo(1);
        assertThat(statistics.get(Rank.MATCH_FIVE_AND_BONUS_NUMBERS)).isEqualTo(1);
        assertThat(statistics.get(Rank.MATCH_FIVE_NUMBERS)).isEqualTo(1);
        assertThat(statistics.get(Rank.MATCH_FOUR_NUMBERS)).isEqualTo(0);
        assertThat(statistics.get(Rank.MATCH_THREE_NUMBERS)).isEqualTo(0);
        assertThat(statistics.get(Rank.MATCH_ZERO_NUMBERS)).isEqualTo(0);
    }
}
