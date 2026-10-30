package services;

import daos.MemberDAO;
import dtosUI.MemberDTOUI;
import repositories.CategoryRepository;
import repositories.MemberRepository;
import repositories.PlayingPositionRepository;

import java.time.LocalDate;
import java.util.Date;

public class MemberService {
    public String addMember(MemberDTOUI member) {
        String message = "";

        PlayingPositionRepository playingPositionRepository = new PlayingPositionRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        MemberRepository memberRepository = new MemberRepository();

        int positionId = playingPositionRepository.positionIdByName(member.getPosition());
        int categoryId = categoryRepository.categoryIdByName(member.getCategory());

        boolean elegibleForInsert = true;

        // Join date has to be before today
        if (member.getJoinDate().after(new Date())) {
            message += "Wrong date\n";
            elegibleForInsert = false;
        }

        // Position name has to exist inside the database or else be null
        if (member.getPosition().isBlank()) {
            member.setPosition(null);
        } else {
            // If playingPositionRepository.positionIdByName() returns 0, position was not found or does not exist inside the DB
            // If it returns -1, an SQLException has been triggered
            if (positionId == 0) {
                message += "Position does not exist\n";
                elegibleForInsert = false;
            } else if (positionId == -1) {
                message += "Something went wrong while fetching position data\n";
                elegibleForInsert = false;
            }
        }

        // Category name has to exist inside the database
        if (member.getCategory().isBlank()) {
            member.setCategory(null);
        } else {
            // If categoryRepository.categoryIdByName() returns 0, category was not found or does not exist inside the DB
            // If it returns -1, an SQLException has been triggered
            if (categoryId == 0) {
                message += "Category does not exist\n";
                elegibleForInsert = false;
            } else if (categoryId == -1) {
                message += "Something went wrong while fetching category data\n";
                elegibleForInsert = false;
            }
        }

        if (elegibleForInsert) {
            MemberDAO memberDAO = new MemberDAO();
            memberDAO.setFirstName(member.getFirstName());
            memberDAO.setLastName(member.getLastName());
            memberDAO.setBirthYear(member.getBirthYear());
            memberDAO.setJoinDate(member.getJoinDate());
            memberDAO.setLeaveDate(member.getLeaveDate());
            memberDAO.setPositionId(positionId);
            memberDAO.setCategoryId(categoryId);

            if (memberRepository.createMemeber(memberDAO)) {
                message += "Member insert successful";
            } else {
                message += "Member has not been inserted";
            }
        }

        return message;
    }
}
