import {Selector} from 'testcafe';
fixture `Manager tests`

fixture `Manager tests`
    .page `http://localhost:3000`;


//don't upload anything
//check manager stats 
test('StatsPerWeekBookingsTest',async t =>{
   
    const stats = Selector(()=>{
        return document.getElementsByTagName('ul')[1];
    })

    await t
    .click('#managerlogin')
    .typeText('#form_description','manager@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(stats.childElementCount).eql(2);
})

//check manager stats details
test('StatsPerWeekBookingsTest',async t =>{
   
    const stats = Selector(()=>{
        return document.getElementsByTagName('ul')[1];
    })
    const details = Selector(()=>{
        return document.getElementsByTagName('svg')[0];
    })

    await t
    .click('#managerlogin')
    .typeText('#form_description','manager@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click(details)
    .expect(stats.childElementCount).eql(3);
})