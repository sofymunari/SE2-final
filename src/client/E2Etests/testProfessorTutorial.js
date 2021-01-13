

fixture `Professor Tutorial`

fixture `Professor Tutorial`
    .page `http://localhost:3000`;

test('ProfessorTutorialTest', async t => {
   
    await t
    .navigateTo('https://www.youtube.com/watch?v=5_J66dQRUuM&t=10s/');

});
