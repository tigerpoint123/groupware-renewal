package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.Class;
import com.ll.groupware_renewal.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamDaoImpl implements TeamJpaRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void InsertTeamInfo(Team team) {
		em.persist(team);
	}

	@Override
	public int SelectClassID(Class classInfo) {
		return em.createQuery("SELECT c.id FROM Class c WHERE c.name = :name", Integer.class)
				.setParameter("name", classInfo.getName())
				.getSingleResult();
	}

	@Override
	public List<Team> SelectTeamList() {
		return em.createQuery("SELECT t FROM Team t", Team.class)
				.getResultList();
	}

	@Override
	public void DeleteTeam(String teamId) {
		Team team = em.find(Team.class, teamId);
		if (team != null) {
			em.remove(team);
		}
	}

	// 나머지 메서드들도 비슷한 방식으로 변환...
}