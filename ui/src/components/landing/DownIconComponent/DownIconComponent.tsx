import React from "react"
import styles from "./DownIconComponent.module.css"
import { Grid, Icon } from "semantic-ui-react"

interface Props {
  name: string
}

export class DownIconComponent extends React.Component<{}, {}> {
  constructor(props: {}) {
    super(props)
  }

  render() {
    return (
      <>
        <Grid centered className={styles.IconContainer}>
          <Icon className={styles.Icon} size="big" name="angle down" />
        </Grid>
      </>
    )
  }
}
