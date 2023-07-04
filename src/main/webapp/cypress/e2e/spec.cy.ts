describe('Login test', () => {
  it('Visits the initial project page', () => {
    cy.visit('/login')
    cy.get('#email').type('admin@admin.com')
    cy.get('#password').type('admin')
    cy.get('button.btn.btn-primary').click()
  })
})