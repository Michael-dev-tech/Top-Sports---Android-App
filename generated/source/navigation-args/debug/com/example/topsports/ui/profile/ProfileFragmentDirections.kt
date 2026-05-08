package com.example.topsports.ui.profile

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.topsports.R

public class ProfileFragmentDirections private constructor() {
  public companion object {
    public fun actionProfileToAddAthlete(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profile_to_addAthlete)
  }
}
