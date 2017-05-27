package p.hh.figrails.commands

import p.hh.figrails.domain.Team
import p.hh.figrails.domain.User


class TeamCommand {
    Team team
    List<UserItemCount> userItemCountList

    static class UserItemCount {
        User user
        Integer count
    }
}
