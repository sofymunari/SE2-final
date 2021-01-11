import {Selector} from 'testcafe';
fixture `Lecture tests`

fixture `Lecture tests`
    .page `http://localhost:3000`;


//BEFORE RUNNING THESE TESTS YOU NEED TO UPLOAD ScheduleInit
test('preparation',async t=>{
    const book = Selector(() => {
        return document.getElementsByTagName('svg')[1];
    })
    await t
    .click('#studentlogin')
    .typeText('#form_description','s0000@studenti.polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click(book)
});

//verify bookings on teacher side
test('LectureHomeTest', async t => {
    const lectures = Selector(()=>{
        return document.getElementsByTagName('ul')[1];
    })
    const svg1 = Selector(()=>{
        return document.getElementsByTagName('svg')[1];
    })
    const bookings = Selector(()=>{
        return document.getElementsByTagName('ul')[1];
    })
    const back = Selector(()=>{
        return document.getElementsByTagName('button')[2];
    })
    
    await t
    .click('#teacherlogin')
    .typeText('#form_description','d0000@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(lectures.childElementCount).eql(15)
    .click(svg1)
    .expect(bookings.childElementCount).eql(2)
    .click(back)
    .expect(lectures.childElementCount).eql(15)
});

//verify modify lecture page
test('LectureHomeTest', async t => {
    const lectures = Selector(()=>{
        return document.getElementsByTagName('ul')[1];
    })
    const modify = Selector(()=>{
        return document.getElementsByTagName('svg')[13];
    })

    const trash_bin = Selector(()=>{
        return document.getElementsByTagName('svg')[1];
    })

    /*const modify_past = Selector(()=>{
        return document.getElementsByTagName('svg')[0];
    })*/

    const remoting = Selector(()=>{
        return document.getElementsByTagName('svg')[0];
    })

    const back = Selector(()=>{
        return document.getElementsByTagName('button')[2];
    })
    
    await t
    .click('#teacherlogin')
    .typeText('#form_description','d0000@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(lectures.childElementCount).eql(15)
    .click(modify)
    .expect(trash_bin.exists).ok();
    //.click(back)
    //.click(modify_past)
    //.expect(remoting.exists).notOk();
});

//delete a lecture
test('LectureDeleteTest', async t => {
    const modify = Selector(()=>{
        return document.getElementsByTagName('svg')[2];
    })
    const trash_bin = Selector(()=>{
        return document.getElementsByTagName('svg')[1];
    })
    const lectures = Selector(()=>{
        return document.getElementsByTagName('ul')[1];
    })
    await t
    .click('#teacherlogin')
    .typeText('#form_description','d0000@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click(modify)
    .click(trash_bin)
    .expect(lectures.childElementCount).eql(14)


});

//make lecture remote
test('LectureRemoteTest', async t => {
    const modify = Selector(()=>{
        return document.getElementsByTagName('svg')[12];
    })
    const remote = Selector(()=>{
        return document.getElementsByTagName('svg')[0];
    })
    const remoting = Selector(()=>{
        return document.getElementsByTagName('svg')[1];
    })
    const back = Selector(()=>{
        return document.getElementsByTagName('button')[2];
    })
    const lecture_remote = Selector(()=>{
        return document.getElementsByTagName('ul')[1].children[13];
    })
   
    await t
    .click('#teacherlogin')
    .typeText('#form_description','d0000@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click(modify)
    .click(remote)
    .expect(remoting.exists).notOk()
    .click(back)
    .expect(lecture_remote.innerText).contains("Remote","expected Remote");
    
});

//check stats for all lectures
test('StatsAllLecturesTest',async t =>{
    
    const stats = Selector(()=>{
        return document.getElementsByTagName('ul')[0];
    })

    await t
    .click('#teacherlogin')
    .typeText('#form_description','d0000@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click('#statsButton')
    .expect(stats.childElementCount).eql(4);
});

//check stats for per week lectures
test('StatsPerWeekBookingsTest',async t =>{
    const stats = Selector(()=>{
        return document.getElementsByTagName('ul')[0];
    })
    const button = Selector(()=>{
        return document.getElementById('Per Week');
    })
    await t
    .click('#teacherlogin')
    .typeText('#form_description','d0000@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click('#statsButton')
    .click(button)
    .expect(stats.childElementCount).eql(3);
})

//check stats for per month lectures
test('StatsPerWeekBookingsTest',async t =>{
    const stats = Selector(()=>{
        return document.getElementsByTagName('ul')[0];
    })
    const button = Selector(()=>{
        return document.getElementById('Per Month');
    })
    await t
    .click('#teacherlogin')
    .typeText('#form_description','d0000@polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click('#statsButton')
    .click(button)
    .expect(stats.childElementCount).eql(2);
})