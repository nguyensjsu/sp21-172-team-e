package com.example.backoffice.cardapi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StarbucksCardRepository extends JpaRepository<StarbucksCard, Long> {

	StarbucksCard findByCardNumber(String cardNumber);
}
