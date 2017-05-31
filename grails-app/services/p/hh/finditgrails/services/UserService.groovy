package p.hh.finditgrails.services

import grails.transaction.Transactional
import p.hh.figrails.domain.Item
import p.hh.figrails.domain.Team
import p.hh.figrails.domain.User

@Transactional
class UserService {

    List<String> allInvolvedPeople(User user) {
        allInvolvedInfo(user, 'involvedPeople')
    }

    List<String> allInvolvedPlaces(User user) {
        allInvolvedInfo(user, 'involvedPlaces')
    }

    List<Team> allAccessibleTeams(User user) {
        user.teams.toList()
    }

    List<Item> allOwnedItems(User user) {
        Item.findAllByOwner(user)
    }

    private List<String> allInvolvedInfo(User user, String fieldName) {
        List<String> allInvolvedInfo = allOwnedItems(user)*."${fieldName}"
        List<String> involvedInfoList = []
        allInvolvedInfo.each {
            involvedInfoList.addAll(it.split(","))
        }
        involvedInfoList = involvedInfoList.unique().sort()
        involvedInfoList
    }

    User findUserByName(String name) {
        User.findByUsername(name)
    }

    User findUserById(Long id) {
        User.findById(id)
    }
}
