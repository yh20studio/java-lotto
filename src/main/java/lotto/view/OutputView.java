package lotto.view;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Rank;

public class OutputView {

    private static final String LOTTO_COUNT_FORMAT = "개를 구매했습니다.";
    private static final String STATISTICS_GUIDE_MESSAGE = "당첨 통계\n---------";
    private static final String YIELD_FORMAT = "총 수익률은 %.2f입니다.";
    private static final String STATISTICS_FORMAT = " (%d원) - %d개";
    private static final String YIELD_GAIN_MESSAGE = "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
    private static final String YIELD_LOSS_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final int YIELD_STANDARD = 1;

    public static void displayLottoTickets(LottoTickets lottoTickets) {
        System.out.println(lottoTickets.getLottoTickets().size() + LOTTO_COUNT_FORMAT);
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            System.out.println(toIntegerNumbers(lottoTicket.getNumbers()).toString());
        }
        System.out.println();
    }

    private static List<Integer> toIntegerNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }

    public static void displayResult(EnumMap<Rank, Integer> statistics, double calculateYield) {
        System.out.println();
        System.out.println(STATISTICS_GUIDE_MESSAGE);
        for (Rank rank : statistics.keySet()) {
            displayStatistics(statistics, rank);
        }
        System.out.println(String.format(YIELD_FORMAT, calculateYield) + isLoss(calculateYield));
    }

    private static void displayStatistics(EnumMap<Rank, Integer> statistics, Rank rank) {
        if (rank.getMatchCount() != Rank.MATCH_ZERO_NUMBERS.getMatchCount()) {
            System.out.println(
                    rank.getMatchStatus() + String.format(STATISTICS_FORMAT, rank.getReward(), statistics.get(rank)));
        }
    }

    private static String isLoss(double calculateYield) {
        if (calculateYield >= YIELD_STANDARD) {
            return YIELD_GAIN_MESSAGE;
        }
        return YIELD_LOSS_MESSAGE;
    }
}
