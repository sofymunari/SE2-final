import {Selector} from 'testcafe';
fixture `Support officer tests`

fixture `Support officer tests`
    .page `http://localhost:3000`;


//BEFORE RUNNING THESE TESTS YOU NEED TO UPLOAD ScheduleInit


//test for the cybersecurity course remotely
test('CybersecurityTest', async t=> {


    const bookings = Selector(() => {
        return document.getElementsByTagName('ul')[1].children[1];
    })

    await t
    .click('#officerlogin')
    .typeText('#form_description','officer@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click('#updatebutton')
    .click('#select')
    .click('#XY0005')
    .click('#submit')
    .click('#logout')
    .click('#teacherlogin')
    .typeText('#form_description','d0003@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(bookings.innerText).contains("Remote","expected to be remote");
})

//test for first year
test('FirstyearTest', async t=> {

    const bookings = Selector(() => {
        return document.getElementsByTagName('ul')[1].children[2];
    })

    await t
    .click('#officerlogin')
    .typeText('#form_description','officer@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click('#updatebutton')
    .click('#firstyear')
    .click('#logout')
    .click('#teacherlogin')
    .typeText('#form_description','d0003@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(bookings.innerText).contains("Remote","expected to be remote");

})

