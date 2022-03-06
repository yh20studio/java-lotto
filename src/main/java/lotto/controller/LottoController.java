package lotto.controller;

import java.util.List;
import lotto.dto.LottoResultDto;
import lotto.dto.LottoTicketsDto;
import lotto.service.LottoService;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public LottoTicketsDto getLottoTickets(int inputMoney, List<List<Integer>> inputNumbers) {
        return lottoService.publishLottoTickets(inputMoney, inputNumbers);
    }

    public LottoResultDto getLottoResult(List<Integer> lottoNumbers, int bonusNumber) {
        return lottoService.matchLottoTickets(lottoNumbers, bonusNumber);
    }
}
