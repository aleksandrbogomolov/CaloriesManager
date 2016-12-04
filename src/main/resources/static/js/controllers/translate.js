'use strict';

app.config(function ($translateProvider) {
    $translateProvider
        .translations('en', {
            TITLE: 'Calories manager'
        })
        .translations('ru', {
            TITLE: 'Менеджер каллорий'
        });
    $translateProvider.preferredLanguage('ru');
});
