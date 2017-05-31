package p.hh.figrails.taglibs

import grails.plugin.springsecurity.SpringSecurityService
import p.hh.figrails.domain.Team
import p.hh.finditgrails.services.UserService

class CommonTagLib {
    static namespace = 'figr'

    UserService userService
    SpringSecurityService springSecurityService

    def involvedPeopleSelect = { attributes, body ->
        List<String> allPeopleList = userService.allInvolvedPeople(springSecurityService.currentUser)

        Map bodyAttr = [:]
        bodyAttr << [ allOptions: allPeopleList ]
        bodyAttr << [ selectedOptions: attributes.selectedOptions ]

        out << "<select class=\"${attributes['class']}\" id=\"${attributes.id}\" name=\"${attributes.name}\" multiple=\"multiple\" />"
        out << involedInfoSelectBody(bodyAttr)
        out << "</select>"
    }

    def involvedPlaceSelect = { attributes, body ->
        List<String> allPlaceList = userService.allInvolvedPlaces(springSecurityService.currentUser)

        Map bodyAttr = [:]
        bodyAttr << [ allOptions: allPlaceList ]
        bodyAttr << [ selectedOptions: attributes.selectedOptions ]

        out << "<select class=\"${attributes['class']}\" id=\"${attributes.id}\" name=\"${attributes.name}\" multiple=\"multiple\" />"
        out << involedInfoSelectBody(bodyAttr)
        out << "</select>"
    }

    def sharedTeamSelect = { attributes, body ->
        List<String> allTeamList = userService.allAccessibleTeams(springSecurityService.currentUser)*.teamName

        Map bodyAttr = [:]
        bodyAttr << [ allOptions: allTeamList ]
        bodyAttr << [ selectedOptions: attributes.selectedOptions ]

        out << "<select class=\"${attributes['class']}\" id=\"${attributes.id}\" name=\"${attributes.name}\" multiple=\"multiple\" />"
        out << involedInfoSelectBody(bodyAttr)
        out << "</select>"
    }

    private def involedInfoSelectBody = { attributes ->
        List<String> allOptions = attributes.allOptions
        List<String> selectedOptions = attributes.selectedOptions

        allOptions.each {
            out << "<option ${it in selectedOptions ? 'selected' : ''}>${it}</option>"
        }
    }

}
