package com.back.catchmate.domain.game.service;

import com.back.catchmate.domain.club.model.Club;
import com.back.catchmate.domain.game.model.Game;
import com.back.catchmate.domain.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public Game findOrCreateGame(Club homeClub, Club awayClub, LocalDateTime gameStartDate, String location) {
        return gameRepository.findByHomeClubAndAwayClubAndGameStartDate(homeClub, awayClub, gameStartDate)
                .orElseGet(() -> {
                    Game newGame = Game.createGame(homeClub, awayClub, gameStartDate, location);
                    return gameRepository.save(newGame);
                });
    }
}
