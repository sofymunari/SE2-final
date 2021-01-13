import {Selector} from 'testcafe';
fixture `Officer tests`

fixture `Officer tests`
    .page `http://localhost:3000`;

//Add Schedule Init

//officer update schedule
test('OfficerUpdateScheduleTest', async t => {
    const updateschedule_link = Selector(()=>{
        return document.getElementsByName('updateschedule_link')[0];
    })

    const edit_schedule_button = Selector(()=>{
        return document.getElementsByName('edit_schedule_button')[0];
    })

    const result = Selector(()=>{
        return document.body;
    })

    await t
    .click('#officerlogin')
    .typeText('#form_description','officer@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click('#updateschedule_button')
    .click(updateschedule_link)
    .click(edit_schedule_button)
    .click('#update_button')
    .expect( Selector(document.body).innerHTML).contains('<div role="alert" class="Toastify__toast-body">edit success</div>');


});