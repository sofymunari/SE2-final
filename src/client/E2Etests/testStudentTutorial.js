import {Selector} from 'testcafe';
import { ClientFunction } from 'testcafe';

fixture `Student Tutorial`

fixture `Student Tutorial`
    .page `http://localhost:3000`;

test('StudentTutorialTest', async t => {

    await t
    .click('#studenttutorial_link')
    .expect(document.location.href).contains('https://www.youtube.com/watch?v=D2xTQ2kcV4w&t=14s');

});
