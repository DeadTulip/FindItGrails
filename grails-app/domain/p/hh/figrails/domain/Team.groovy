package p.hh.figrails.domain

class Team {

    String teamName
    Set<User> members = []
    Set<Item> items = []

    static hasOne = [creator: User]
    static hasMany = [members: User, items: Item]
    static belongsTo = [User]

    static mapping = {
        table 'team'
        teamName column: 'teamname'
        creator column: 'creator'
        members joinTable: [name: 'team_user', key: 'team_id']
        items joinTable: [name: 'team_item', key: 'team_id']
    }

    static constraints = {
        teamName nullable: false
        creator nullable: false
    }
}
