import {Selector} from 'testcafe';
fixture `Authentication tests`

fixture `Authentication tests`
    .page `http://localhost:3000`;

//correct student authentiction
test('StudentLoginTest', async t => {
      await t
      .click('#studentlogin')
      .typeText('#form_description','s0003@studenti.polito.it')
      .typeText('#form_project','password')
      .click('#submit_button')
      .expect(Selector('#matricola').innerText).eql('s0003');

});

//wrong username student authentication
test('StudentLoginFail1Test', async t => {
    await t
    .click('#studentlogin')
    .typeText('#form_description','wrong')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(Selector('#wrongAuthentication').innerText).eql('wrong username or password');

});

//wrong password student authentication
test('StudentLoginFail2Test', async t => {
    await t
    .click('#studentlogin')
    .typeText('#form_description','s0003@stduenti.polito.it')
    .typeText('#form_project','wrong')
    .click('#submit_button')
    .expect(Selector('#wrongAuthentication').innerText).eql('wrong username or password');

});

//correct teacher authentication
test('TeacherLoginTest', async t => {
    await t
    .click('#teacherlogin')
    .typeText('#form_description','d0001@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(Selector('#professorInfo').innerText).eql('Giorgio Bruno');

});

//wrong username teacher authentication
test('TeacherLoginFail1Test', async t => {
    await t
    .click('#teacherlogin')
    .typeText('#form_description','wrong')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(Selector('#wrongAuthentication').innerText).eql('wrong username or password');

});

//wrong password teacher authentication
test('TeacherLoginFail2Test', async t => {
    await t
    .click('#teacherlogin')
    .typeText('#form_description','d0001@polito.it')
    .typeText('#form_project','wrong')
    .click('#submit_button')
    .expect(Selector('#wrongAuthentication').innerText).eql('wrong username or password');

});


//corret Manager authentication
test('ManagerLoginTest', async t => {
    
    await t
    .click('#managerlogin')
    .typeText('#form_description','manager@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(Selector('#managerInfo').innerText).eql('Giuseppe Conte');

});

//wrong username Manager authentication
test('ManagerLoginFail1Test', async t => {
    
    await t
    .click('#managerlogin')
    .typeText('#form_description','wrong')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(Selector('#wrongAuthentication').innerText).eql('wrong username or password');

});


//wrong password Manager authentication
test('ManagerLoginFail2Test', async t => {
    
    await t
    .click('#managerlogin')
    .typeText('#form_description','manager@polito.it')
    .typeText('#form_project','wrong')
    .click('#submit_button')
    .expect(Selector('#wrongAuthentication').innerText).eql('wrong username or password');

});


//corret Officer authentication
test('OfficerLoginTest', async t => {
    
    await t
    .click('#officerlogin')
    .typeText('#form_description','officer@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(Selector('#officerInfo').innerText).eql('Mario Rossi');

});

//wrong username Manager authentication
test('OfficerLoginFail1Test', async t => {
    
    await t
    .click('#officerlogin')
    .typeText('#form_description','wrong')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(Selector('#wrongAuthentication').innerText).eql('wrong username or password');

});


//wrong password Manager authentication
test('OfficerLoginFail2Test', async t => {
    
    await t
    .click('#officerlogin')
    .typeText('#form_description','officer@polito.it')
    .typeText('#form_project','wrong')
    .click('#submit_button')
    .expect(Selector('#wrongAuthentication').innerText).eql('wrong username or password');

});