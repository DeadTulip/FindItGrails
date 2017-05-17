package p.hh.figrails.domain

class Team {

    String teamName
    User creator
    Set<User> members = []

    static hasMany = [members: User]

    static mapping = {
        table 'team'
        teamName column: 'teamname'
        creator column: 'creator'
        members joinTable: [name: 'team_user', key: 'team_id']
    }

    static constraints = {
        teamName nullable: false
        creator nullable: false
    }
}
