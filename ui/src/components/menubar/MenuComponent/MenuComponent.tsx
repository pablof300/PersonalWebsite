import React from "react"
import styles from "./MenuComponent.module.css"
import { Grid, Menu } from "semantic-ui-react"

interface Props {
  name: string
}

interface State {
  count: number
  developerName: string
}

export class MenuComponent extends React.Component<{}, State> {
  constructor(props: {}) {
    super(props)
  }

  render() {
    return (
      <>
        <Grid className={styles.MainContainer}>
          <Grid.Column width={10} />
          <Menu text stackable>
            <Menu.Item href="#">About me</Menu.Item>
            <Menu.Item href="#projects">Projects</Menu.Item>
            <Menu.Item href="#skills">Skills</Menu.Item>
            <Menu.Item href="#resume">Resume</Menu.Item>
            <Menu.Item href="#contact">Contact</Menu.Item>
            <Menu.Item href="#projects">Blog</Menu.Item>
          </Menu>
        </Grid>
      </>
    )
  }
}
