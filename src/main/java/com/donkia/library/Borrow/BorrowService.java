package com.donkia.library.Borrow;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowService {

    private final BorrowRepository borrowRepository;

    public void borrowBook(){

    }

    public void returnBook(){

    }


}
