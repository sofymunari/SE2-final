import {Selector} from 'testcafe';
fixture `Officer tests`

fixture `Officer tests`
    .page `http://localhost:3000`;

//Nothing to do before

//officer update schedule
test('OfficerUpdateScheduleTest', async t => {

    await t
    .click('#officerlogin')
    .typeText('#form_description','officer@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click('#updateschedule_button')
    .click('#updateschedule_link')


});