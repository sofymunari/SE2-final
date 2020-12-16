import {Selector} from 'testcafe';
fixture `Booking tests`

fixture `Booking tests`
    .page `http://localhost:3000`;


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
    .expect(bookings.childElementCount).eql(6)
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
    .expect(bookings.childElementCount).eql(4)
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