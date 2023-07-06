describe('Login test OK', () => {
  it('Visits the initial project page', () => {
    cy.visit('/login')
    cy.get('#email').type('admin@admin.com')
    cy.get('#password').type('admin')
    cy.get('button.btn.btn-primary').click()
    cy.wait(1000)
    cy.url().should('include', '/cursos')
  })
})

describe('Login test ERROR', () => {
  it('Visits the initial project page', () => {
    cy.visit('/login')
    cy.get('#email').type('admin@admin.com')
    cy.get('#password').type('123')
    cy.get('button.btn.btn-primary').click()

    cy.url().should('include', '/login')
  })
})