import {Selector} from 'testcafe';
fixture `Lecture tests`

fixture `Lecture tests`
    .page `http://localhost:3000`;


//booking on student home page
test('LectureHomeTest', async t => {
    const lectures = Selector(()=>{
        return document.getElementsByTagName('ul')[1];
    })
    const svg1 = Selector(()=>{
        return document.getElementsByTagName('svg')[1];
    })

    const svg2 = Selector(()=>{
        return document.getElementsByTagName('svg')[2];
    })
    
    const back = Selector(()=>{
        return document.getElementsByTagName('button')[2];
    })
    
    await t
    .click('#teacherlogin')
    .typeText('#form_description','p0000@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(lectures.childElementCount).eql(5)
    .click(svg1)
    .expect(svg1.exists).ok()
    .click(back)
    .click(svg2)
    .expect(svg2.exists).ok()
});

//delete a lecture
test('LectureDeleteTest', async t => {
    const svg1 = Selector(()=>{
        return document.getElementsByTagName('svg')[1];
    })
    const lectures = Selector(()=>{
        return document.getElementsByTagName('ul')[1];
    })
    await t
    .click('#teacherlogin')
    .typeText('#form_description','p0000@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click(svg1)
    .click(svg1)
    .expect(lectures.childElementCount).eql(4)


});

//make lecture remote
test('LectureDeleteTest', async t => {
    const svg1 = Selector(()=>{
        return document.getElementsByTagName('svg')[1];
    })
    const svg2 = Selector(()=>{
        return document.getElementsByTagName('svg')[2];
    })
   
    await t
    .click('#teacherlogin')
    .typeText('#form_description','p0000@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click(svg1)
    .click(svg1)
    .expect(svg2.exists).notOk()
    


});