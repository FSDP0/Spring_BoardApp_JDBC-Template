package com.boardapp.boardfe.board.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.boardapp.boardfe.board.model.Board;

@FeignClient(name = "BOARD-CLIENT", url = "${api.url}")
public interface BoardClient {

    @GetMapping
    public List<Board> getAllBoards();

    @GetMapping("/:{id}")
    public Board getBoardById(@PathVariable Long id);

    @PostMapping
    public String createBoardPost(@RequestBody Board dto);

    @PutMapping("/:{id}")
    public String editBoardPost(@PathVariable Long id, @RequestBody Board dto);

    @DeleteMapping("/:{id}")
    public String deleteBoardPost(@PathVariable Long id);

}
