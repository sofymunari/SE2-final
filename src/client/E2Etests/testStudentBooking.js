import {Selector} from 'testcafe';
fixture `Booking tests`

fixture `Booking tests`
    .page `http://localhost:3000`;


//BEFORE RUNNING THESE TESTS YOU MUST UPLOAD FILE: scheduleInit


//booking on student home page
test('BookingHomeTest', async t => {

    const bookings = Selector(() => {
        return document.getElementsByTagName('ul')[1];
    })
    await t
    .click('#studentlogin')
    .typeText('#form_description','s0000@studenti.polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .expect(Selector('#main').exists).ok()
    .expect(bookings.childElementCount).eql(44)
    .click('#show_bookings')
    .expect(bookings.childElementCount).eql(2)

});




//booking task
test('BookTaskTest', async t => {

    const book = Selector(() => {
        return document.getElementsByTagName('svg')[0];
    })
    
    const bookings = Selector(() => {
        return document.getElementsByTagName('ul')[1];
    })
    await t
    .click('#studentlogin')
    .typeText('#form_description','s0000@studenti.polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click(book)
    .click(book)
    .expect(bookings.childElementCount).eql(42)
    .click('#show_bookings')
    .expect(bookings.childElementCount).eql(4)
    

});

//delete Booking
test('BookTaskTest', async t => {

    const trash_bin = Selector(() => {
        return document.getElementsByTagName('svg')[1];
    })
    const bookings = Selector(() => {
        return document.getElementsByTagName('ul')[1];
    })

    await t
    .click('#studentlogin')
    .typeText('#form_description','s0000@studenti.polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click('#show_bookings')
    .click(trash_bin)
    .expect(bookings.childElementCount).eql(3)
});


//waiting list Booking prparation
test('BookTaskTest', async t => {

    const book = Selector(() => {
        return document.getElementsByTagName('svg')[1];
    })
    const bookings = Selector(() => {
        return document.getElementsByTagName('ul')[1].children[1];
    })

    await t
    .click('#studentlogin')
    .typeText('#form_description','s0003@studenti.polito.it')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click(book)
    .click('#logout')
    .click('#studentlogin')
    .typeText('#form_description','silviogiro@gmail.com')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click(book)
    .click('#logout')
    .click('#studentlogin')
    .typeText('#form_description','tony.y.saliba00@gmail.com')
    .typeText('#form_project','password')
    .click('#submit_button')
    .click(book)
    .click('#show_bookings')
    .expect(bookings.innerText).contains("yes","expected Waiting list");

});
