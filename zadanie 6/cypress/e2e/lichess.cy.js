describe('Lichess.org', () => {

    it('home page', () => {
        cy.visit('https://lichess.org');

        cy.title().should('include', 'lichess');

        cy.get('.site-title').should('be.visible');
    });

    it('puzzle page', () => {
        cy.visit('https://lichess.org/training');

        cy.title().should('include', 'lichess');

        cy.get('.puzzle-play').should('be.visible');
    });

    it('user (wmakoss) page in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/@/wmakoss');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'wmakoss');

        cy.get('.profile-side > .user-infos > .stats > .thin').should('include.text',"Zarejestrowano 28 wrz 2022")
    });

    it('analysis page in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/analysis');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'Analiza');

        cy.get('.switch').click();

        cy.get('.switch + pearl').should('have.text',"+0.2");
    });

    it('games page in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/games');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'Partie w toku');
    });

    it('games page bullets in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/games');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'Partie w toku');

        cy.get('a[href="/games/bullet"]').click();

        cy.title().should('include', 'Bullet');
    });

    it('spect games page in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/games/bullet');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'Partie w toku');
        cy.title().should('include', 'Bullet');

        cy.get('.now-playing > .mini-game').first().click();

        cy.title().should('include', 'spectator');
    });

    it('analysis page easy mat in one in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/analysis/2k5/6R1/8/8/7R/8/8/2K5_w_-_-_0_1?color=white');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'Analiza');

        cy.get('.switch').click();

        cy.get('.switch + pearl').should('have.text',"#1");

        cy.get('.analyse__tools .pv-san').should('have.text',"Rh8#");
    });

    it('analysis page easy mat in one v2 in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/analysis/r1bqkbnr/ppp2ppp/2np4/4p3/2B1P3/5Q2/PPPP1PPP/RNB1K1NR_w_KQkq_-_0_4?color=white');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'Analiza');

        cy.get('.switch').click();

        cy.get('.switch + pearl').should('have.text',"#1");

        cy.get('.analyse__tools .pv-san').should('have.text',"Qxf7#");
    });

    it('analysis page test3 in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/analysis/8/4b3/6p1/2p2p2/4k3/2R2NP1/r4P1P/5K2_w_-_-_4_50?color=white');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'Analiza');

        cy.get('.switch').click();

        cy.get('.analyse__tools .pv-san').should('include.text',"Re3+");
    });

    it('analysis page test4 in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/analysis/fromPosition/1k3r2/p6Q/1p6/1P1qb3/P2p2Pp/3R3P/8/3R2K1_b_-_-_5_38');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'Analiza');

        cy.get('.switch').click();

        cy.get('.switch + pearl').should('have.text',"#-3");
        cy.get('.analyse__tools .pv-san').should('include.text',"Bh2+");
    });

    it('analysis page test5 in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/TaPf3frn/white#146');

        cy.title().should('include', 'lichess');

        cy.get('.switch').click();

        cy.wait(5000);

        cy.get('.switch + pearl').should('have.text',"#7");
        cy.get('.analyse__tools .pv-san').should('include.text',"Qxf4");
    });

    it('fide player page Magnus Carlsen in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/fide/1503014/Carlsen_Magnus');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'Carlsen, Magnus');

        cy.get('.fide-player__federation').should('include.text',"Norway");
        cy.get('.fide-player__card').should('include.text',"1503014");
        cy.get('.utitle').should('have.text',"GM");
    });

    it('check lag page in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/lag');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'lagging?');

        cy.get('.short').should('include.text',"Nie. Twoje połączenie jest szybkie.");
    });

    it('analysis page Masters database in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/analysis');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'Analiza');

        cy.get('.fbt[title="Biblioteka otwarć i końcówek"]').click();

        cy.get('.moves').should('include.text',"e4");
    });

    it('main page check langauge in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/');

        cy.title().should('include', 'lichess');

        cy.wait(500);
        cy.get('.toggle').click();
        cy.get('.subs > :nth-child(1)').click();

        cy.get('.current').should('include.text',"Polski");
    });

    it('opening page french defense in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/opening');

        cy.title().should('include', 'lichess');

        cy.get('.opening__search-form__input').type('french defense');

        cy.get('.opening__search__results > :nth-child(1)').click();
        
        cy.location('href').should('eq', 'https://lichess.org/opening/French_Defense/e4_e6');

        cy.get('.button[href="/training/French_Defense_Other_variations"]').should('exist');
        cy.get('.button[href="/analysis/pgn/e4_e6#explorer"]').should('exist');
    });

    it('learn page in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/learn#/1');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'Ucz się szachów');

        cy.wait(500);
        
        cy.get('.next').click();
        
        cy.wait(500);

        cy.get('.rook').click();
        
        cy.get('.has-item').click();

        cy.location('href').should('eq', 'https://lichess.org/learn#/1/2');
    });

    it('team page "polski związek" in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/team/search?text=polski+zwi%C4%85zek');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'polski związek');

        cy.get('.slist').should('include.text', 'Polski Związek Szachowy');

    });

    it('team page "polski związek" in polish language', () => {

        cy.intercept('GET', '**', (req) => {
            req.headers['Accept-Language'] = 'pl-PL';
        }).as('getWithLanguageHeader');

        cy.visit('https://lichess.org/team/search?text=polski+zwi%C4%85zek');

        cy.title().should('include', 'lichess');
        cy.title().should('include', 'polski związek');

        cy.get('.slist .team-name').first().click();

        cy.get('.text').should('include.text', 'Polski Związek Szachowy');

    });
});
