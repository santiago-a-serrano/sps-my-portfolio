// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomFact() {
  const facts =
      ['I\'m currently studying some TensorFlow!', 
      'I was born in Bogota, Colombia',
      'I started learning to code because I wanted to create my own videogame!',
      'I love 70\'s disco and English rock!',
      'I love running and going to the gym'];

  // Pick a random greeting.
  const fact = facts[Math.floor(Math.random() * facts.length)];

  // Add it to the page.
  const factContainer = document.getElementById('fact-container');
  factContainer.innerText = fact;
}

var nightModeEnabled = false;
function nightModeToggle() {
    if(!nightModeEnabled){
        document.getElementById("bootstrap-stylesheet").href="style/bootstrap.min-dark.css"
    } else {
        document.getElementById("bootstrap-stylesheet").href="style/bootstrap.min-light.css"
    }
    nightModeEnabled = !nightModeEnabled
};
