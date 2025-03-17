package com.example.branchee_back.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.branchee_back.entity.Chat;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository  extends JpaRepository<Chat,Integer>{
}
