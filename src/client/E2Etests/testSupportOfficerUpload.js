import {Selector} from 'testcafe';
fixture `Officer tests`

fixture `Officer tests`
    .page `http://localhost:3000`;

//Nothing to do before

//officer upload
test('OfficerLoginTest', async t => {
    

    await t
    .click('#officerlogin')
    .typeText('#form_description','officer@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .setFilesToUpload('#studentsfile','../../../InitFiles/StudentsInit.csv')
    .click('#upload_students')
    .click('#logout')
    .click('#studentlogin')
    .typeText('#form_description','s0003@studenti.polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(Selector('#matricola').innerText).eql('s0003');

});



//officer upload Holidays
test('OfficerUploadHolidaysTest', async t => {
    await t
    .click('#officerlogin')
    .typeText('#form_description','officer@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .setFilesToUpload('#studentsfile','../../InitFiles/holidays.csv')
    .click('#upload_holidays')
    .expect(Selector(document.body).innerHTML).contains('<div role="alert" class="Toastify__toast-body">upload success</div>');

});

