package p.hh.figrails.controllers

import p.hh.figrails.domain.Item

class ItemController {

    def add() {

        render(view: 'updateItem')
    }

    def list() {
        List<Item> items = []
        items << new Item(
               name: 'file1.pdf', dateCreated: new Date(), dateUpdated: new Date(), type: 'pdf', size: 1000
        )
        items << new Item(
                name: '123.jpg', dateCreated: new Date()-1, dateUpdated: new Date()-1, type: 'jpg', size: 2000
        )
        items << new Item(
                name: 'grails.pdf', dateCreated: new Date()-3, dateUpdated: new Date()-2, type: 'pdf', size: 3000
        )
        items << new Item(
                name: 'java.txt', dateCreated: new Date()-2, dateUpdated: new Date()-1, type: 'txt', size: 4000
        )
        items << new Item(
                name: 'file2.pdf', dateCreated: new Date()-4, dateUpdated: new Date()-2, type: 'pdf', size: 5000
        )

        render(view: 'listItem', model: [items: items])
    }
}
