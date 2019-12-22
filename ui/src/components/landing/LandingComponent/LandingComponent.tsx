import React from 'react';
import styles from './LandingComponent.module.css'
import { Grid, Image, Segment } from 'semantic-ui-react'
import { MenuComponent } from '../../menubar/MenuComponent/index'
import { AboutComponent } from '../AboutComponent/index'
import { ProjectsComponent } from '../projects/ProjectsComponent/index'
import { DownIconComponent } from '../DownIconComponent/index'
import { SkillsComponent } from '../skills/SkillsComponent/index'

interface Props {
  name: string
}

interface State {
  count: number
  developerName: string
}

export class LandingComponent extends React.Component<{}, State> {

  constructor(props: {}) {
    super(props)
  }

  render() {
    return (
      <>
      <MenuComponent />
      <AboutComponent />
      <DownIconComponent />
      <ProjectsComponent />
      <DownIconComponent />
      <SkillsComponent />
      <DownIconComponent />
      </>
    )
  }
}
