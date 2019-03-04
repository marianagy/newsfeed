package com.project.newsfeed.dao.user;

import com.project.newsfeed.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Integer> {
}
