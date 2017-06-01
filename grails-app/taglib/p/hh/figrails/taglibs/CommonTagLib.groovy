package p.hh.figrails.taglibs

import grails.plugin.springsecurity.SpringSecurityService
import p.hh.figrails.domain.Team
import p.hh.finditgrails.services.UserService

class CommonTagLib {
    static namespace = 'figr'

    UserService userService
    SpringSecurityService springSecurityService

    def involvedPeopleSelect = { attributes, body ->
        if(attributes.disabled == true) {
            involvedInfoSelect(attributes, attributes.selectedOptions)
        } else {
            List<String> allPeopleList = userService.allInvolvedPeople(springSecurityService.currentUser)
            involvedInfoSelect(attributes, allPeopleList)
        }

    }

    def involvedPlaceSelect = { attributes, body ->
        if(attributes.disabled == true) {
            involvedInfoSelect(attributes, attributes.selectedOptions)
        } else {
            List<String> allPlaceList = userService.allInvolvedPlaces(springSecurityService.currentUser)
            involvedInfoSelect(attributes, allPlaceList)
        }
    }

    def sharedTeamSelect = { attributes, body ->
        if(attributes.disabled == true) {
            involvedInfoSelect(attributes, attributes.selectedOptions)
        } else {
            List<String> allTeamList = userService.allAccessibleTeams(springSecurityService.currentUser)*.teamName
            involvedInfoSelect(attributes, allTeamList)
        }
    }

    private String involvedInfoSelect(Map attributes, List allOptions) {
        List<String> selectedOptions = attributes.selectedOptions

        attributes << [multiple: "multiple"]
        attributes.remove("selectedOptions")
        String attrString = generateAttributeString(attributes)

        out << "<select ${attrString} />"
        allOptions.each {
            out << "<option ${it in selectedOptions ? 'selected' : ''}>${it}</option>"
        }
        out << "</select>"
    }

    String generateAttributeString(Map attributes) {
        StringBuffer sb = new StringBuffer()
        attributes.each { key, value ->
            if(key == 'disabled') {
                if (value == true) {
                    sb.append("${key}=\"${value}\" ")
                }
            } else {
                sb.append("${key}=\"${value}\" ")
            }
        }
        sb.toString()
    }
}
