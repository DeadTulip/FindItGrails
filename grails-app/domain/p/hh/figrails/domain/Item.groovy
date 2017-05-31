package p.hh.figrails.domain

class Item {

    User owner
    Location location
    String name
    Date dateCreated
    Date dateUpdated
    Date eventStartTime
    Date eventEndTime
    String involvedPeople
    String involvedPlaces
    String description

    static hasMany = [sharedTeams: Team]

    static mapping = {
        table 'item'
        tablePerHierarchy true

        name column: 'name'
        dateCreated column: 'date_created'
        dateUpdated column: 'date_updated'
        eventStartTime column: 'event_start_time'
        eventEndTime column: 'event_end_time'
        involvedPeople column: 'people'
        involvedPlaces column: 'places'
        description column: 'description'
        discriminator column: 'item_type'

        sharedTeams joinTable: [name: 'team_item', key: 'item_id']
    }

    static constraints = {
        owner nullable: false
        location nullable: false
        name nullable: false, maxSize: 200
        dateCreated nullable: false
        dateUpdated nullable: false
        involvedPeople maxSize: 5000
        involvedPlaces maxSize: 5000
        description maxSize: 5000
    }
}
