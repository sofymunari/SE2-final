import {Selector} from 'testcafe';
import { ClientFunction } from 'testcafe';

fixture `Professor Tutorial`

fixture `Professor Tutorial`
    .page `http://localhost:3000`;

test('ProfessorTutorialTest', async t => {

    await t
    .click('#professortutorial_link')
    .expect(document.location.href).contains('https://www.youtube.com/watch?v=5_J66dQRUuM&t=10s');

});
