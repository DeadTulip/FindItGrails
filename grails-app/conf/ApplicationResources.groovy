modules = {

    'jquery' {
        resource url: [plugin: 'jquery', dir: 'js/jquery/', file: 'jquery-1.11.1.js']
    }

    'bootstrap' {
        resource url: [plugin: 'twitter-bootstrap', dir: 'css', file: 'bootstrap.min.css']
    }

    'multiselect' {
        resource url: [dir: 'css/lib', file: 'select2.min.css']
        resource url: [dir: 'js/lib', file: 'select2.full.min.js']
    }

    'bootstrap-table' {
        dependsOn 'jquery'

        resource url: [dir: 'css/lib', file: 'bootstrap-table.css']
        resource url: [dir: 'js/lib', file: 'bootstrap-table.js']
    }

    'openItem' {
        dependsOn 'multiselect', 'jquery-ui'

        resource url: [dir: 'js/figrails/', file: 'openItem.js']
    }

    'listItems' {
        dependsOn 'bootstrap-table'

        resource url: [dir: 'js/figrails/', file: 'listItems.js']
    }

    'userInfo' {
        dependsOn 'bootstrap-table', 'jquery-ui'

        resource url: [dir: 'js/figrails/', file: 'userInfo.js']
    }

    'teamInfo' {
        dependsOn 'bootstrap-table', 'jquery-ui'

        resource url: [dir: 'js/figrails/', file: 'teamInfo.js']
    }
}
