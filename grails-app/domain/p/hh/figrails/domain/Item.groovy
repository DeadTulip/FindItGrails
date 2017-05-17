package p.hh.figrails.domain

class Item {

    User owner
    Location location
    String name
    Date dateCreated
    Date dateUpdated
    String type
    Long size
    Date eventStartTime
    Date eventEndTime
    String involvedPeople
    String involvedPlaces
    String description

    static mapping = {
        table 'item'
        name column: 'name'
        dateCreated column: 'date_created'
        dateUpdated column: 'date_updated'
        type column: 'type'
        size column: 'size'
        eventStartTime column: 'event_start_time'
        eventEndTime column: 'event_end_time'
        involvedPeople column: 'people'
        involvedPlaces column: 'places'
        description column: 'description'
    }

    static constraints = {
        owner nullable: false
        location nullable: false
        name nullable: false, maxSize: 200
        dateCreated nullable: false
        dateUpdated nullable: false
        type maxSize: 20
        involvedPeople maxSize: 5000
        involvedPlaces maxSize: 5000
        description maxSize: 5000
    }
}
