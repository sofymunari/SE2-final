

fixture `Student Tutorial`

fixture `Student Tutorial`
    .page `http://localhost:3000`;

test('StudentTutorialTest', async t => {

    await t
    
    .navigateTo('https://www.youtube.com/watch?v=D2xTQ2kcV4w&t=14s');

});
