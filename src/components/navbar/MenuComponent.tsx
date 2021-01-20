import React from 'react';
import { Grid, Menu } from "semantic-ui-react"
import './MenuComponent.css'

const MenuComponent = () => {
    return (
        <>
          <Grid stackable className="Menu">
            <Grid.Column floated='right' width={5}>
              <Menu text stackable>
                <Menu.Item href="/">About me</Menu.Item>
                <Menu.Item href="/#experience">Experience</Menu.Item>
                <Menu.Item href="/#projects">Projects</Menu.Item>
                <Menu.Item href="#skills">Skills</Menu.Item>
                <Menu.Item href="#resume">Resume</Menu.Item>
                <Menu.Item href="#contact">Contact</Menu.Item>
              </Menu>
            </Grid.Column>
          </Grid>
        </>
      )
}

export { MenuComponent }